CycliGo-backend

Build by cyclists for cyclists

create cycligo database:
docker run -p 3306:3306 --name cycligo-mysql -e LANG=C.UTF-8 -e MYSQL_ROOT_PASSWORD=test123 -d mysql:latest

docker exec -it cycligo-mysql bash (connects to docker terminal)
mysql -uroot -ptest123 -e"create database cycligo"

Useful docker commands:
docker ps -a (shows all created containers)
docker system prune (remove all unused containers)
docker -rm cycligo-mysql (delete docker VM)
