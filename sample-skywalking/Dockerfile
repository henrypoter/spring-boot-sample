FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER nfky(yanpp@nfky.com)

ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="-Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom"
ENV SKYWALKING_AGENT="-javaagent://skywalking/agent/skywalking-agent.jar"

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /sample-skywalking/agent

WORKDIR /sample-skywalking

EXPOSE 8092

ADD ./target/sample-skywalking.jar ./

COPY agent/ /sample-skywalking/agent/

CMD java $SKYWALKING_AGENT $JAVA_OPTS -jar keyu4cloud-gateway.jar
