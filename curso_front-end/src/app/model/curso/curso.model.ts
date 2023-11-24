import { AlunoModule } from "../aluno/aluno.module";

export interface CursoModule {
    id?:number;
    dtCriacao?:Date;
    nome?:string;
    descricao?:string;
    duracao?:string;
    alunosMatriculados:Array<AlunoModule>;
}