package org.msa.msa5spring.controller;


import org.msa.msa5spring.request.QueryRequestDTO;
import org.msa.msa5spring.response.GlobalResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ResponseController {
    @GetMapping("/res")
    public ResponseEntity<GlobalResponseDTO> index(){
       //return ResponseEntity.status(200).body("400") ; //body에 담기는 데이터타입<String>과 일치해야함
        //return  ResponseEntity.ok("3");
        //return ResponseEntity.notFound().build(); // 404

        GlobalResponseDTO<String> globalResponseDTO = new GlobalResponseDTO<>("00", "정상처리", "레스폰스 테스트");
        return  ResponseEntity.status(200).body(globalResponseDTO);

    }
    @GetMapping("/res/test")
    public ResponseEntity<GlobalResponseDTO<QueryRequestDTO>> test(){
            return ResponseEntity
                    .status(200)
                    .body(new GlobalResponseDTO<>( "00", "정상처리", new QueryRequestDTO(3L, "홍길동")
                     )
            );
    }
}
