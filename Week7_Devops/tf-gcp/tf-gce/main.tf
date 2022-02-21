terraform {
  required_providers {
    google = {
      source = "hashicorp/google"
      version = "4.11.0"
    }
  }
}

// configure the terraform google provider
provider "google" {
  credentials = file("javasre-creds.json")
  project = "javasre"
  region = "us-central1"
}

// Terraform plugin for creating random ids
resource "random_id" "instance_id" {
  byte_length = 8
}

// create a vpc for our resources
resource "google_compute_network" "vpc_network" {
  name = "terraformed-network"
}

// A single Compute Engine instance
resource "google_compute_instance" "terraformed_instance" {
  name          = "javasre-vm-${random_id.instance_id.hex}"
  machine_type  =  "e2-micro"
  zone          = "us-central1-a"
  tags     = ["http-server"]

  boot_disk {
    initialize_params {
        image = "ubuntu-1804-lts"
    }
  }

    // install java runtime environment
    metadata_startup_script = file("metadata_script.sh")

    network_interface {
        network = google_compute_network.vpc_network.name

        access_config {
            // Include this section to give the VM an external ip address
        }
    }

    metadata = {
      "ssh-keys" = "august.duet:${file("~/.ssh/id_ed25519.pub")}"
    }
}

resource "google_compute_firewall" "terraformed_firewall" {
  name    = "http-8080-ingress"
  network = "default"
  source_ranges = ["0.0.0.0/0"]
  target_tags = ["http-server"]

  allow {
    protocol = "tcp"
    ports    = ["8080"]
  }
}

output "ip" {
  value = google_compute_instance.terraformed_instance.network_interface.0.access_config.0.nat_ip
}