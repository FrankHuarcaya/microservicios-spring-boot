package com.microservicios.app.cursos.api;

import com.microservicios.app.cursos.domain.services.ICursoService;
import com.microservicios.app.cursos.dto.CursoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final ICursoService cursoService;

    public CursoController(ICursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public ResponseEntity<List<CursoDto>> listCursos() {
        List<CursoDto> cursos = cursoService.listCursos();
        return ResponseEntity.ok(cursos);
    }


    @PostMapping
    public ResponseEntity<CursoDto> registerCurso(@RequestBody CursoDto cursoDto) {
        CursoDto newCurso = cursoService.registerCurso(cursoDto);
        return ResponseEntity.ok(newCurso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoDto> updateCurso(@PathVariable Long id, @RequestBody CursoDto cursoDto) {
        CursoDto updatedCurso = cursoService.updateCurso(id, cursoDto);
        return ResponseEntity.ok(updatedCurso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Long id) {
        cursoService.deleteCurso(id);
        return ResponseEntity.noContent().build();
    }

}
