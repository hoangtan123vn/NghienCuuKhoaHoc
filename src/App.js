import logo from './icon-xe.jpg';
import './App.css';
import { useState,useEffect} from 'react'
import axios from 'axios'

function App() {
  const [articleId, setArticleId] = useState('');
  const [ email,setEmail] = useState ('')
  const [ password, setPassword] = useState('')
  const [ success, setSuccess] = useState(false);

  console.log('id :  ',articleId )

  const changeHandler = ( event) =>{
    console.log('msg:   ',event.target.value)
    setEmail(event.target.value)
  }
  const changePasswordHandler = (event)=>{
    setPassword(event.target.value)
    console.log('pass:   ',event.target.value)
  }
  const loginHandler = async(event) =>{
    event.preventDefault();
    const article = {"email" : email,"password": password };
    console.log(article)
    const response = await axios.post('https://reqres.in/api/login', article);
    if(response) setSuccess(true);
    setArticleId(response.data.token)
    console.log('res:   ', response.data.token)
   
  }


  console.log('email' , email)
  return (
      <div className="container">
          <form id='form-login' onSubmit={loginHandler} >
          <h1>Transport</h1>
              
              <input class ='form-username'
                type='text' 
                placeholder='Tên đăng nhập' 
                value={email}
                onChange ={changeHandler}
              >   
              </input>
              
              <input class ='form-password'
              placeholder='Mật khẩu'
              type= 'password'
              value= {password}
              onChange = {changePasswordHandler}
              >
              
              </input>
              <button class ='form-button'
                type='submit'
              >
                Đăng nhập
              </button>
              Token id: {articleId}
          </form>
          <div className="card-body">
              
              {success && <h1> Đăng nhập thành công</h1>}
          </div>
          </div>
      
  );
}

export default App;
