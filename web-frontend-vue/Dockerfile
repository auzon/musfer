FROM node:25-alpine3.22 AS builder
WORKDIR /app
COPY ./package*.json ./
RUN npm install
COPY . .
RUN npm run build

FROM node:25-alpine3.22 AS development
WORKDIR /app
COPY --from=builder /app /app
CMD [ "npm", "run", "dev", "--", "--host" ]

FROM nginx:stable-alpine AS production
COPY --from=builder /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]