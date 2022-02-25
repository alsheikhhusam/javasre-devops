terraform {
  required_providers {
      docker = {
          source = "kreuzwerker/docker"
          version = ">=2.13.0"
      }
  }
}

provider "docker" {
    host = "npipe:////.//pipe//docker_engine"
  
}

resource "docker_image" "nginx" { # Similar to docker pull
    name            = "nginx:latest"
    keep_locally    = false
}

resource "docker_container" "nginx" { # Similar to docker run
  image = docker_image.nginx.latest
  name = var.container_name
  ports {
      internal = 80
      external = 8000
  }
}