# 基础镜像
FROM docker.io/node:10.24.1 AS build
WORKDIR /app
ENV API_BASE_URL http://localhost:8080/workflow
COPY ./ .
RUN npm rebuild node-sass
RUN npm install && npm run build:prod

FROM docker.io/nginx
RUN mkdir /app
COPY --from=build /app/dist /app
COPY nginx.conf /etc/nginx/nginx.conf