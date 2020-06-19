cd C:\Program Files (x86)\OWASP\Zed Attack Proxy
cls
zap.bat -daemon -port 8081 -host localhost -config api.key=APIkey -config api.addrs.addr.name=.* -config api.addrs.addr.regex=true
