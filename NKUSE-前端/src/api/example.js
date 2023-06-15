import request from '@/utils/request'

export default{
    getAllQuestions(searchModel){
        return request({
            url: '/cet/objQues/getAll',
            method: 'get',
            params:{
                pageNo: searchModel.pageNo,
                pageSize: searchModel.pageSize,
            }
        }
        );
    },
    getOneQuestion(searchModel){
        return request({
            url: '/cet/objQues/getOne',
            method: 'get',
            params:{
                quesId: searchModel.quesId,
            }
        }
        );
    },
    getOneQuestion_2(id){
        return request({
            url: '/cet/objQues/getOne',
            method: 'get',
            params:{
                quesId: id,
            }
        }
        );
    },
}