import { useState } from 'react';
import './Login.css';
// import {fazendoLogin} from '../utils/api';
import imgLogin from '../imagens/logoClinicaYouX.png'

const LoginForm = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        if (username === '' || password === '') {
            setError('Por favor, preencha todos os campos.');
        } else {
            setError('');
            console.log('Usuário:', username);
            console.log('Senha:', password);
        }
    };

    // const enviandoDadosParaLogin = () => {
    //     fazendoLogin(username, password)
    // }

    return (
        <div className="login-form">
            <img
                src={imgLogin}
                style={{width: 250, margin: 0}}
                alt="Logo Hospital"
                className="logo-hospital"
            />
            <h2>Login</h2>
            {error && <p className="error">{error}</p>}
            <form onSubmit={handleSubmit} style={{ width: '100%' }}>
                <div className="input-group">
                    <label htmlFor="username">Usuário</label>
                    <input
                        type="text"
                        id="username"
                        placeholder="Digite seu usuário"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        autoComplete="username"
                    />
                </div>
                <div className="input-group">
                    <label htmlFor="password">Senha</label>
                    <input
                        type="password"
                        id="password"
                        placeholder="Digite sua senha"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        autoComplete="current-password"
                    />
                </div>
                <button type="submit" className="button">Entrar</button>
            </form>
            <div className="login-footer">
                © {new Date().getFullYear()} Clínica Hospitalar. Todos os direitos reservados.
            </div>
        </div>
    );
};

export default LoginForm;