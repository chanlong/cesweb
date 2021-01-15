FROM cesweb/java:8

MAINTAINER chen.long@cesgroup.com.cn

ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="-Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom"

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

EXPOSE 5020

COPY maven /maven/

CMD java $JAVA_OPTS -jar /maven/cesweb-cloud-console.jar
