#指定java8镜像
FROM anapsix/alpine-java:8_server-jre_unlimited

#维护人的信息
MAINTAINER erp

#创建目录
RUN mkdir -p /erp-base

#创建的目录，作为工作目录
WORKDIR /erp-base

EXPOSE 4000

ADD ./erp-base-biz/target/erp-base-biz.jar ./

CMD java -Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom -jar erp-base-biz.jar
