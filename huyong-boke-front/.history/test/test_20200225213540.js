let arr1 = [1,2,3,4]
let arr2 = [1,2,3,4]
arr1.push.apply(arr1,arr2)
console.log(arr1)
f1()
function f1() {
  console.log(arguments[0])
}
