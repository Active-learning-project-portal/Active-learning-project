function numberCluster(array){
    const results = [];

    for(let val of array)
        if(val % 2 === 0) results.push(val)
    
    for(let val of array)
        if(val % 2 !== 0) results.push(val)
    
    return results;
}

// DOT NOT TOUCH THIS LINE!!!
module.exports = { numberCluster }