cd C:\Program Files (x86)\OWASP\Zed Attack Proxy
cls
zap.bat -daemon -port 8081 -host localhost -config com.hellosign.api.key=APIkey -config com.hellosign.api.addrs.addr.name=.* -config com.hellosign.api.addrs.addr.regex=true
