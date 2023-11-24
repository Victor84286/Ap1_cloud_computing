import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CursoService } from 'src/app/services/curso.service';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {

  curso: any;

  constructor(private cursoService: CursoService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    let id = this.route.snapshot.params["id"];
    this.cursoService.getCursoById(id).subscribe(Response => {
      this.curso = Response;
      console.log(this.curso);
    });
  }

}
