@echo off
"C:\Program Files\Java\jdk-17\bin\java.exe" -Dmaven.multiModuleProjectDirectory=C:\Users\extmb\Desktop\MyAEBackEnd -Djansi.passthrough=true -Dmaven.home=C:\Users\extmb\.m2\wrapper\dists\apache-maven-3.9.5-bin\2adeog8mj13csp1uusqnc1f2mo\apache-maven-3.9.5 -Dclassworlds.conf=C:\Users\extmb\.m2\wrapper\dists\apache-maven-3.9.5-bin\2adeog8mj13csp1uusqnc1f2mo\apache-maven-3.9.5\bin\m2.conf "-Dmaven.ext.class.path=C:\Program Files\JetBrains\IntelliJ IDEA 2023.3.4\plugins\maven\lib\maven-event-listener.jar" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.3.4\lib\idea_rt.jar=53068:C:\Program Files\JetBrains\IntelliJ IDEA 2023.3.4\bin" -Dfile.encoding=UTF-8 -classpath C:\Users\extmb\.m2\wrapper\dists\apache-maven-3.9.5-bin\2adeog8mj13csp1uusqnc1f2mo\apache-maven-3.9.5\boot\plexus-classworlds-2.7.0.jar;C:\Users\extmb\.m2\wrapper\dists\apache-maven-3.9.5-bin\2adeog8mj13csp1uusqnc1f2mo\apache-maven-3.9.5\boot\plexus-classworlds.license org.codehaus.classworlds.Launcher -Didea.version=2023.3.4 clean package -DskipTests
docker build --file C:\Users\extmb\Desktop\MyAEBackEnd\Dockerfile -t myaebe:latest C:\Users\extmb\Desktop\MyAEBackEnd
echo "Password for Docker Hub"
docker login --username merionard
docker tag myaebe:latest merionard/myaebe:latest
docker push merionard/myaebe:latest
echo "Fini !"
pause