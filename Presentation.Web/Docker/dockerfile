FROM localhost:8082/microsoft/dotnet:latest

ADD output /test

EXPOSE 5000

WORKDIR /test
CMD dotnet exec Presentation.Web.dll

