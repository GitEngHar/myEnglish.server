# myEnglish.server
MyEnglish.Server

## 設計


### システム関連図
![MyEnglishシステム関連 drawio](https://github.com/user-attachments/assets/00126fa1-37d4-4219-b5be-d14c037ffdb5)

### ユースケース図
![MyEnglishユースケース drawio](https://github.com/user-attachments/assets/be3442c3-d2d3-4e3a-8019-96aff34f9de0)

### オブジェクト図
![MyEnglishオブジェクト drawio](https://github.com/user-attachments/assets/09be72d8-9bbe-41f7-9f2f-1b36ac9bfd47)

### ドメインモデル
![MyEnglishドメインモデル drawio](https://github.com/user-attachments/assets/b9e108db-c0dd-4aa7-9c1a-6dde4803103e)

## K8s検証用

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

local kube-app 検証用 port転送設定
```shell
kubectl port-forward service/myenglish-server-service -nmyenglish  8080:8080
kubectl port-forward deployment/myenglish-front-deployment -nmyenglish  3000:80
```

local-app 検証用 port転送設定
```shell
kubectl port-forward deployment/myenglish-db-deployment -nmyenglish  3306:3306
kubectl port-forward deployment/myenglish-redis-deployment -nmyenglish  6800:6379
```

