FROM tomcat:9.0-jdk11-adoptopenjdk-hotspot

# Remove the default ROOT webapp
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copy the generated WAR file to the webapps directory of Tomcat
COPY build/libs/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
