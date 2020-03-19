import axios from 'axios';
const axiosService = axios.create({
  baseURL: 'http://localhost:8081'
})
axiosService.interceptors.request.use(config => {
  con
})
export default axiosService
