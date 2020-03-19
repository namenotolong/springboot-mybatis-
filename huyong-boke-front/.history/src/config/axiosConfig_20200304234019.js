import axios from 'axios';
const axiosService = axios.create({
  baseURL: 'http://localhost:8081'
})
axiosService.interceptors.request.use(config => {
  console.log('进入拦截器')
  return config
}, err=>{
  console.log(err)
  return 
})
export default axiosService
