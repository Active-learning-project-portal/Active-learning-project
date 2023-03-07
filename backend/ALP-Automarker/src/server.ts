import express, { Application } from 'express'
import { Server } from "http"
import cors from "cors"
import { route } from "./router"

const app: Application = express();
const server: Server = new Server(app);

app.set('PORT', process.env.PORT || 3000)
app.use(cors())
app.use(express.json())
app.use("/automarker", route)

server.listen(app.get('PORT'), () => console.log(`Server running on port ${app.get('PORT')}`))