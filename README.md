# spring-boot-redis-cluster
3 master 3 slave node.

## Run Project on local with Docker

Run single Redis Service.
```bash
  docker-compose -f .\docker-compose-dev.yaml up -d  
``` 

![Screenshot_4](https://user-images.githubusercontent.com/21373505/219664965-6f8c286f-3e76-4041-8d1d-1f57d2f6f616.png)
![Screenshot_6](https://user-images.githubusercontent.com/21373505/219666576-00114fe5-152e-4ada-ad41-aaeab28a7f6f.png)


## Run Project on Production

Build.
```bash
   mvn clean package -P prod
```
Run Redis Cluster and Spring App.
```bash
   docker-compose up -d
```

![Screenshot_2](https://user-images.githubusercontent.com/21373505/219664956-c1b8f085-aa71-45a5-aa2e-b16342541bbc.png)
![Screenshot_3](https://user-images.githubusercontent.com/21373505/219664962-46ced410-de6b-4767-a373-1bdef093b5f0.png)

## Set data and get cached data

```bash
curl http://localhost:8080/api/cache/set
curl http://localhost:8080/api/cache/get
```

![Screenshot_5](https://user-images.githubusercontent.com/21373505/219665744-3173d75b-c150-42d9-9150-451eb8bb4b36.png)
