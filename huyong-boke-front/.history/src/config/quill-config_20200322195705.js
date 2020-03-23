import AxiosConfig from './axiosConfig' // 这里使用axios封装了http请求
import {baseURL,getStore} from '../utils/utils'

/* 富文本编辑图片上传配置 */
const uploadConfig = {
  name: 'file', // 必填参数 文件的参数名
  accept: 'image/png, image/gif, image/jpeg, image/bmp, image/x-icon' // 可选 可上传的图片格式
}

// toolbar工具栏的工具选项（默认展示全部）
const toolOptions = [
  ['bold', 'italic', 'underline', 'strike'],
  ['blockquote', 'code-block'],
  [{ 'header': 1 }, { 'header': 2 }],
  [{ 'list': 'ordered' }, { 'list': 'bullet' }],
  [{ 'script': 'sub' }, { 'script': 'super' }],
  [{ 'indent': '-1' }, { 'indent': '+1' }],
  [{ 'direction': 'rtl' }],
  [{ 'size': ['small', false, 'large', 'huge'] }],
  [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
  [{ 'color': [] }, { 'background': [] }],
  [{ 'font': [] }],
  [{ 'align': [] }],
  ['clean'],
  ['link', 'image']
]
const handlers = {
  image: function image () {
    var self = this
    var fileInput = this.container.querySelector('input.ql-image[type=file]')
    if (fileInput === null) {
      alert(1)
      fileInput = document.createElement('input')
      fileInput.setAttribute('type', 'file')
      // 设置图片参数名
      if (uploadConfig.name) {
        fileInput.setAttribute('name', uploadConfig.name)
      }
      // 可设置上传图片的格式
      fileInput.setAttribute('accept', uploadConfig.accept)
      fileInput.classList.add('ql-image')
      // 监听选择文件
      fileInput.addEventListener('change', async () => {
        const formData = new FormData()
        formData.append('file', fileInput.files[0])
        let res = await new AxiosConfig().axiosService.post('/file/uploadBar', formData)
        let length = 0
        if (res.code === '0') { // 相当于接口成功回调
          length = self.quill.getSelection(true).index
        }
        self.quill.insertEmbed(length, 'image', res.data.picUrl)
        self.quill.setSelection(length + 1)
        fileInput.value = ''
      })
      this.container.appendChild(fileInput)
    }
    fileInput.click()
  }
}

export default {
  theme: 'snow', // 主题
  modules: {
    toolbar: {
      container: toolOptions, // 工具栏选项
      handlers: handlers // 事件重写
    }
  },
  placeholder: '从这儿开始'
}
