import axios from 'axios'

export default {
    getDoctorInfo (doctorId) {
        return axios.get(`/api/doctor/${doctorId}`)
    },
    getDoctorList () {
        return axios.get('/api/doctor')
    }
}

