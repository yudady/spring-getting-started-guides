https://minikube.sigs.k8s.io/docs/handbook/registry/

使用私有註冊表
GCR/ECR/ACR/Docker：minikube 有一個插件，registry-creds它將憑證映射到 minikube 以支持從 Google Container Registry (GCR)、Amazon 的 EC2 Container Registry (ECR)、Azure Container Registry (ACR) 和 Private Docker registries 中提取。您將需要跑步minikube addons configure registry-creds並minikube addons enable registry-creds開始跑步。下面是一個例子：



https://github.com/GoogleContainerTools/jib/blob/master/jib-maven-plugin/README.md#auth-object


Example
In this configuration, the image:

Is built from a base of openjdk:alpine (pulled from Docker Hub)
Is pushed to localhost:5000/my-image:built-with-jib, localhost:5000/my-image:tag2, and localhost:5000/my-image:latest
Runs by calling java -Dmy.property=example.value -Xms512m -Xdebug -cp app/libs/*:app/resources:app/classes mypackage.MyApp some args
Exposes port 1000 for tcp (default), and ports 2000, 2001, 2002, and 2003 for udp
Has two labels (key1:value1 and key2:value2)
Is built as OCI format
