
Check some sources:  
http://mindbowser.com/deploying-go-application-on-aws-ec2-server/  
deploying on t2.micro:  
https://hackernoon.com/deploying-a-go-application-on-aws-ec2-76390c09c2c5  

# Build

To build a go app I prefer to use a docker image. 
https://hub.docker.com/_/golang?tab=description


Assume we have an app, named "app"..  
run the image with attched volume with golang sources:  
```sh
docker run -it --name golang -v /home/username/workspace/sandbox/go/:/go/src/app golang /bin/bash
```

By default in the image the GOPATH is already set. But if it is not or needs to be changed, than it can be done by:  

```sh
export GOPATH="/go"
```

Installing "app" in go:  
```sh
cd src/app
go install app
```

build  
```sh
go build -o app
```


