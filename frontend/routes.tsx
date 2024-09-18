import ChatView from 'Frontend/views/chat/ChatView';
import MainLayout from 'Frontend/views/MainLayout.js';
import { lazy } from 'react';
import { createBrowserRouter, RouteObject } from 'react-router-dom';
import StreamingChatView from "Frontend/views/streaming-chat/StreamingChatView";
import AngryTaxiDriverStreamingChatView from "Frontend/views/angry-taxi-driver-steaming-chat/AngryTaxiDriverStreamingChatView";
import GreedyCarpetStreamingChatView from "Frontend/views/greedy-carpet-seller-chat/GreedyCarpetStreamingChatView"


export const routes: RouteObject[] = [
  {
    element: <MainLayout />,
    handle: { title: 'Main' },
    children: [
      { path: '/', element: <ChatView />, handle: { title: 'Chat' } },
      { path: '/streaming', element: <StreamingChatView />, handle: { title: 'Streaming Chat' } },
      { path: '/angry-taxi-driver-chat', element: <AngryTaxiDriverStreamingChatView />, handle: { title: 'Angry Taxi Driver Streaming Chat' } },
      { path: '/greedy-carpet-seller-chat', element: <GreedyCarpetStreamingChatView />, handle: { title: 'Greedy Carpet Streaming Chat' } },
    ],
  },
];

export default createBrowserRouter(routes);
