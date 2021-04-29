# k8s-java-config-reload

## Description

## Usage

```
# Start minikube k8s
minikube start
eval $(minikube docker-env)

# Build image in minikube Docker daemon
mvn compile jib:dockerBuild

# Apply resources
kubectl apply -f k8s/k8s-java-config-reload-configmap.yaml
kubectl apply -f k8s/k8s-java-config-reload-pod.yaml
```

## Resources

- https://kubernetes.io/docs/tasks/configure-pod-container/configure-pod-configmap/#mounted-configmaps-are-updated-automatically