import axios from 'axios';
const axiosService = axios.create({
  baseURL: 'http://localhost:8081'
})
axiosService.interceptors.request.use(config => {
  console.log('进入request拦截器')
  return config
}, err=>{
  console.log(err)
  return Promise.reject(err)
})
axiosService.interceptors.response.use(config => {
  console.log('进入response拦截器')
  return config
}, err=>{
  console.log(err)
  return Promise.reject(err)
})
export default axiosService
