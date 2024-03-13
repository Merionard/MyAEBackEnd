@echo off
"C:\Program Files\Java\jdk-17\bin\java.exe" -Dmaven.multiModuleProjectDirectory=E:\dev\myAe\myAEBackEnd -Djansi.passthrough=true -Dmaven.home=C:\Users\bibi\.m2\wrapper\dists\apache-maven-3.9.5-bin\2adeog8mj13csp1uusqnc1f2mo\apache-maven-3.9.5 -Dclassworlds.conf=C:\Users\bibi\.m2\wrapper\dists\apache-maven-3.9.5-bin\2adeog8mj13csp1uusqnc1f2mo\apache-maven-3.9.5\bin\m2.conf "-Dmaven.ext.class.path=C:\Program Files\JetBrains\IntelliJ IDEA 2023.3.3\plugins\maven\lib\maven-event-listener.jar" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.3.3\lib\idea_rt.jar=30284:C:\Program Files\JetBrains\IntelliJ IDEA 2023.3.3\bin" -Dfile.encoding=UTF-8 -classpath C:\Users\bibi\.m2\wrapper\dists\apache-maven-3.9.5-bin\2adeog8mj13csp1uusqnc1f2mo\apache-maven-3.9.5\boot\plexus-classworlds-2.7.0.jar;C:\Users\bibi\.m2\wrapper\dists\apache-maven-3.9.5-bin\2adeog8mj13csp1uusqnc1f2mo\apache-maven-3.9.5\boot\plexus-classworlds.license org.codehaus.classworlds.Launcher -Didea.version=2023.3.3 --offline clean package -DskipTests
docker build --file E:\dev\myAe\myAEBackEnd\Dockerfile -t myaebe:latest .
echo "Password for Docker Hub"
docker login --username merionard
docker tag myaebe:latest merionard/myaebe:latest
docker push merionard/myaebe:latest
echo "Fini !"
pause