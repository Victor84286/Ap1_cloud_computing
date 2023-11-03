import { AfterViewInit, Component, OnDestroy, OnInit } from '@angular/core';
import { CursoService } from '../services/curso.service';
import { Curso } from '../model/curso.model';

@Component({
  selector: 'app-curso',
  templateUrl: './curso.component.html',
  styleUrls: ['./curso.component.css']
})
export class CursoComponent implements OnInit {
  cursos: Curso[] = [];

  constructor(private cursoService: CursoService) {

  }

  ngOnInit(): void {
    this.cursoService.getCurso().subscribe(response => {
      this.cursos = response as any;
    });
  }
/*
  ngOnDestroy(): void {

  }

  ngAfterViewInit(): void {

  }
  */
}
