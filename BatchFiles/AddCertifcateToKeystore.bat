cd C:\Program Files\Java\jdk1.8.0_191\bin
keytool -delete -alias owasp -keystore C:\Program Files\Java\jdk-13.0.2\jre\lib\security\cacerts -storepass changeit
keytool -import -alias owasp -keystore C:\Program Files\Java\jdk-13.0.2\jre\lib\security\cacerts -file C:\Users\LAVRO002\Downloads\rootcert.cer  -storepass changeit -noprompt


