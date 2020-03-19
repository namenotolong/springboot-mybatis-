let user = {};
function say() {
  console.log('hello world')
}
user.say = function(){console.log(111)}
user.say()
