rm -rf /volume1/docker/ZeitverwaltungBackend/Node.js-Server
docker run -t --rm -v /volume1/docker/ZeitverwaltungBackend:/root -v /volume1/docker/ZeitverwaltungBackend:/git alpine/git clone https://github.com/EderRene/Node.js-Server.git
cp /volume1/docker/ZeitverwaltungBackend/Docker.txt /volume1/docker/ZeitverwaltungBackend/Node.js-Server/Docker.txt
docker stop zeitverwaltungsbackend
docker rm zeitverwaltungsbackend
docker rmi zeitverwaltungbackend
sudo docker build -t zeitverwaltungsbackend -f /volume1/docker/ZeitverwaltungBackend/Node.js-Server/Docker.txt /volume1/docker/ZeitverwaltungBackend/Node.js-Server
docker run -p 8888:3000 -t -d --name zeitverwaltungsbackend --link postgres-zeitverwaltungs-docker:postgres zeitverwaltungsbackend
rm -rf /volume1/docker/ZeitverwaltungBackend/Nodejs-Server
