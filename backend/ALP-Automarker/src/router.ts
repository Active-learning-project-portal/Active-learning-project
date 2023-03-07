import { Router, Request, Response } from "express"
import * as fs from "fs"
import * as path from "path"
import { projectSolutions } from "./tests/solutions/solutions"

const route: Router = Router()

const respondToClient = (
    res: Response,
    name: string,
    status: "PASSED" | "FAILED",
    error: any) => {
    res.send({
        name: name,
        status: status,
        error: error
    })
}

route.get('/', (req: Request, res: Response) => {
    res.send("WELCOME TO THE ALP AUTOMARKING SERVER")
})

route.post("/solution1", (req: Request, res: Response) => {
    // Taking the passed solution model
    const { body: { solution } } = req
    const { project1: { input, expected } } = projectSolutions
    const title: string = "Solution 1 Report"
    const moduleInclusion: string = "\n// DOT NOT TOUCH THIS LINE!!!\nmodule.exports = { numberCluster }"

    // Writing the solution in the file
    fs.writeFileSync(path.join(__dirname, 'tests', 'projects', 'project1.js'),
        solution+moduleInclusion, { encoding: 'utf-8' })

    // Testing the user's solution
    import("./tests/projects/project1").then(testFile => {
        try {
            const results: Array<number> = testFile.numberCluster(input)

            for (let index in expected) {
                if (expected[+index] !== results[+index]) {
                    return respondToClient(res, title, "FAILED", null)
                }
            }

            respondToClient(res, title, "PASSED", null)
        }
        catch (err) {
            respondToClient(res, title, "FAILED", err)
        }
    }).catch(err => {
        respondToClient(res, title, "FAILED", err)
    })
})

export { route }