# Variables
variable "project_id" {
  description = "windy-album-339219"
}

variable "region" {
  description = "us-central1"
}

variable "credentials" {
  description = "windy-album-339219-creds.json"
}

# Configuration
provider "google" {
  project     = var.project_id
  credentials = file(var.credentials)
  region      = var.region
}

# vpc
resource "google_compute_network" "vpc" {
  name                    = "${var.project_id}-vpc"
  auto_create_subnetworks = false
}

#subnet
resource "google_compute_subnetwork" "subnet" {
  name          = "${var.project_id}-subnet"
  region        = var.region
  network       = google_compute_network.vpc.name
  ip_cidr_range = "10.10.0.0/24"
}