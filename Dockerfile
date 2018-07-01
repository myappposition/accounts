FROM busybox

ADD target/${JAR_FILE} /tmp/

WORKDIR /tmp/
RUN mkdir /tmp/app/

RUN unzip *.jar -d /tmp/app/
RUN mv /tmp/app/BOOT-INF/lib /tmp/lib #move libs to external folder for caching




###############################################################


FROM 2alab/openjdk-8-springboot

#cache libs layer
COPY --from=0  /tmp/lib /deployments/BOOT-INF/lib
COPY --from=0  /tmp/app /deployments
