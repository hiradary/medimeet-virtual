import axios from 'axios'

export default class DoctorService {
    getDoctorList () {
        return axios.get('/api/v1/doctor')
    }
}
