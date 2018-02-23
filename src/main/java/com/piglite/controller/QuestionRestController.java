package com.piglite.controller;

import com.piglite.Dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.piglite.model.Question;
import com.piglite.service.QuestionService;

import java.util.List;

@RestController
@RequestMapping("/qe")
public class QuestionRestController {

    @Autowired
    private static QuestionDao userService;  //Service which will do all data retrieval/manipulation work

    static {
        userService = new QuestionDao();
    }

       //-------------------Retrieve All Questions--------------------------------------------------------

    @RequestMapping(value = "/quest/", method = RequestMethod.GET)
    public ResponseEntity<List<Question>> listAllQuestions() {

        List<Question> questions = userService.findAllQuestions();
        if(questions.isEmpty()){
            return new ResponseEntity<List<Question>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Question>>(questions, HttpStatus.OK);
    }



    //-------------------Retrieve Single Question--------------------------------------------------------

    @RequestMapping(value = "/quest/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Question> getQuestion(@PathVariable("id") int id) {
        System.out.println("Fetching Question with id " + id);
        Question question = userService.findById(id);
        if (question == null) {
            System.out.println("Question with id " + id + " not found");
            return new ResponseEntity<Question>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Question>(question, HttpStatus.OK);
    }



    //-------------------Create a Question--------------------------------------------------------

    @RequestMapping(value = "/quest/", method = RequestMethod.POST)
    public ResponseEntity<Void> createQuestion(@RequestBody Question question,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Question " + question.getQname());

//        if (userService.isQuestionExist(question)) {
//            System.out.println("A Question with name " + question.getQname() + " already exist");
//            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//        }

        userService.saveQuestion(question);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/quest/{id}").buildAndExpand(question.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }



    //------------------- Update a Question --------------------------------------------------------

    @RequestMapping(value = "/quest/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Question> updateQuestion(@PathVariable("id") int id, @RequestBody Question question) {
        System.out.println("Updating Question " + id);

        Question currentQuestion = userService.findById(id);

        if (currentQuestion==null) {
            System.out.println("Question with id " + id + " not found");
            return new ResponseEntity<Question>(HttpStatus.NOT_FOUND);
        }

        currentQuestion.setQname(question.getQname());

        userService.updateQuestion(currentQuestion);
        return new ResponseEntity<Question>(currentQuestion, HttpStatus.OK);
    }



    //------------------- Delete a Question --------------------------------------------------------

    @RequestMapping(value = "/quest/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Question> deleteQuestion(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting Question with id " + id);

        userService.delById(id);
        return new ResponseEntity<Question>(HttpStatus.NO_CONTENT);
    }

}
