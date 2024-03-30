import axios from 'axios'
class UserService {
    getUserList() {
        return axios.get(process.env.BASE_URL + '/api/users')
    }
}

export default new UserService()