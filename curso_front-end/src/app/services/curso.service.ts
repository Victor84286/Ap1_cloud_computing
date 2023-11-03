import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Curso } from '../model/curso.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CursoService {

  constructor(private httpClient: HttpClient) {  }

  public getCurso(): Observable<Curso[]> {
    return this.httpClient.get<Curso[]> ('http://localhost:8080/curso')
  }
}
