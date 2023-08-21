This is a demo project with SpringBoot, Docker and Kafka. The recent updates on Wikimedia are sent to a Kafka topic in real time, via a Kafka Producer. Kafka and Zookeeper run in Docker, configured via docker-compose. 

**To start Zookeper and Kafka**

At the root of the project run the command

    docker-compose up -d

For this to work you will need Docker Desktop installed with the daemon running

Zookeeper and Kafka containers on Docker
![](C:\Users\Aby\IdeaProjects\kafka-wikimedia-producer\images\kafka-on-docker.JPG)

Once Kafka is running, run the producer application

**KafkaWikimediaProducerApplication.main()**

The Producer should start sending the wikimedia recent updates to the Kafka topic **wikimedia-updates**
