import {AppLayout} from '@hilla/react-components/AppLayout.js';
import {DrawerToggle} from '@hilla/react-components/DrawerToggle.js';
import Placeholder from 'Frontend/components/placeholder/Placeholder';
import {useRouteMetadata} from 'Frontend/util/routing';
import {Suspense} from 'react';
import {NavLink, Outlet} from 'react-router-dom';

const navLinkClasses = ({ isActive }: any) => {
  return `block rounded-m p-s ${isActive ? 'bg-primary-10 text-primary' : 'text-body'}`;
};

export default function MainLayout() {
  const currentTitle = useRouteMetadata()?.title ?? 'My App';
  return (
    <AppLayout primarySection="drawer">
      <div slot="drawer" className="flex flex-col justify-between h-full p-m">
        <header className="flex flex-col gap-m">
          <h1 className="text-l m-0">💻 AI-Assistant Chat</h1>
          <nav>
            <NavLink className={navLinkClasses} to="/">
              Chat
            </NavLink>
            <NavLink className={navLinkClasses} to="/streaming">
              Streaming Chat
            </NavLink>
            <NavLink className={navLinkClasses} to="/angry-taxi-driver-chat">
              Angry taxi driver chat
            </NavLink>
            <NavLink className={navLinkClasses} to="/greedy-carpet-seller-chat">
              Greedy carpet seller chat
            </NavLink>
          </nav>
        </header>
      </div>

      <DrawerToggle slot="navbar" aria-label="Menu toggle"></DrawerToggle>
      <h2 slot="navbar" className="text-l m-0">
        {currentTitle}
      </h2>

      <Suspense fallback={<Placeholder />}>
        <Outlet />
      </Suspense>
    </AppLayout>
  );
}
