# myEnglish.server
MyEnglish.Server

## DockerCommand

```shell
aws ecr get-login-password --region ap-northeast-1 | docker login --username AWS --password-stdin ${AWS_ACCOUNTID}.dkr.ecr.ap-northeast-1.amazonaws.com
```

docker repository の 認証情報でsecretを作る
```shell
kubectl create secret generic regcred -nmyenglish \
    --from-file=.dockerconfigjson=/Users/${USER_NAME}/.docker/config.json \
    --type=kubernetes.io/dockerconfigjson
```

arm,amd両方対応image
```shell
docker buildx create --use 
docker buildx build --platform linux/amd64,linux/arm64 -t haruapp/myenglish-server:latest --push .
```

arm image backend
```shell
docker buildx build --platform linux/arm64 -t haruapp/myenglish-server:latest . 
```


colima memmory 逼迫した時の対応
```shell
colima stop
vim ~/.colima/default/colima.yaml  # memmoty をよしなにかえる
colima start
```

local 検証用 port転送設定
```shell
kubectl port-forward service/myenglish-server-service -nmyenglish  8080:8080
kubectl port-forward deployment/myenglish-front-deployment -nmyenglish  8082:80
```