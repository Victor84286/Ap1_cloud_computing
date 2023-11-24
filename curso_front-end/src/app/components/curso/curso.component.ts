import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CursoModule } from 'src/app/model/curso/curso.model';
import { CursoService } from 'src/app/services/curso.service';

@Component({
  selector: 'app-curso',
  templateUrl: './curso.component.html',
  styleUrls: ['./curso.component.css']
})
export class CursoComponent implements OnInit {
  cursos: CursoModule[] = [];

  constructor(private cursoService: CursoService, private router: Router) { }

  ngOnInit(): void {
    this.cursoService.getCurso().subscribe(response => {
      this.cursos = response as any;
    });
  }

  redirectToDetail(id: any){
    this.router.navigate(["detail", id]);
  }
}
