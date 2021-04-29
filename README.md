# k8s-java-config-reload

## Description

## Usage

```
# Start minikube k8s
minikube start
eval $(minikube docker-env)

# Build image in minikube Docker daemon
mvn compile jib:dockerBuild
```