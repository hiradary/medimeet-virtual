import axios from 'axios'

export default class UserService {
    getUserList () {
        return axios.get('/api/v1/users')
    }
}
