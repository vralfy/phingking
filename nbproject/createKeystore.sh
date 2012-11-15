rm private/keystore
keytool -genkeypair -storepass phingking -alias phingking -validity 730 -keystore private/keystore -dname "CN=Norman Specht, OU=Unknown, O=www.foopara.de, L=Unkown, ST=Unknown, C=DE"
