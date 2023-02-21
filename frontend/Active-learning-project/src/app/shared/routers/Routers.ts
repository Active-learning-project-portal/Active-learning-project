// User authentication routing flow
const auth = {
    rootPath: 'auth',
    person: 'user',
    action: {
        signin: 'signin',
        signout: 'signout',
        signup: 'signup',
        forgotPassword: 'forgotPassword'
    }
}

// App routing flow
const app = {
    rootPath: 'app',
}

export { auth, app }