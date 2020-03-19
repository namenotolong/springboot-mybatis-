let arr1 = [1,2,3,4]
let arr2 = [1,2,3,4]
console.log(typeof arr1)
arr1.push.apply(arr1,arr2)
console.log(arr1)

