import axios from 'axios';

export default axios.create({
    baseURL:'http://localhost:9090',
    headers: {"ngrok-skip-browser-warning": "true"}
});