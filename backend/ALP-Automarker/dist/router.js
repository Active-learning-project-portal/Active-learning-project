"use strict";
var __createBinding = (this && this.__createBinding) || (Object.create ? (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    var desc = Object.getOwnPropertyDescriptor(m, k);
    if (!desc || ("get" in desc ? !m.__esModule : desc.writable || desc.configurable)) {
      desc = { enumerable: true, get: function() { return m[k]; } };
    }
    Object.defineProperty(o, k2, desc);
}) : (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    o[k2] = m[k];
}));
var __setModuleDefault = (this && this.__setModuleDefault) || (Object.create ? (function(o, v) {
    Object.defineProperty(o, "default", { enumerable: true, value: v });
}) : function(o, v) {
    o["default"] = v;
});
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (k !== "default" && Object.prototype.hasOwnProperty.call(mod, k)) __createBinding(result, mod, k);
    __setModuleDefault(result, mod);
    return result;
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.route = void 0;
const express_1 = require("express");
const fs = __importStar(require("fs"));
const path = __importStar(require("path"));
const solutions_1 = require("./tests/solutions/solutions");
const route = (0, express_1.Router)();
exports.route = route;
const respondToClient = (res, name, status, error) => {
    res.send({
        name: name,
        status: status,
        error: error
    });
};
route.get('/', (req, res) => {
    res.send("WELCOME TO THE ALP AUTOMARKING SERVER");
});
route.post("/solution1", (req, res) => {
    // Taking the passed solution model
    const { body: { solution } } = req;
    const { project1: { input, expected } } = solutions_1.projectSolutions;
    const title = "Solution 1 Report";
    const moduleInclusion = "\n// DOT NOT TOUCH THIS LINE!!!\nmodule.exports = { numberCluster }";
    // Writing the solution in the file
    fs.writeFileSync(path.join(__dirname, 'tests', 'projects', 'project1.js'), solution + moduleInclusion, { encoding: 'utf-8' });
    // Testing the user's solution
    Promise.resolve().then(() => __importStar(require("./tests/projects/project1"))).then(testFile => {
        try {
            const results = testFile.numberCluster(input);
            for (let index in expected) {
                if (expected[+index] !== results[+index]) {
                    return respondToClient(res, title, "FAILED", null);
                }
            }
            respondToClient(res, title, "PASSED", null);
        }
        catch (err) {
            respondToClient(res, title, "FAILED", err);
        }
    }).catch(err => {
        respondToClient(res, title, "FAILED", err);
    });
});
